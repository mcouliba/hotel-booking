import React from 'react';
import ValidationWizardBookContents from './ValidationWizardBookContents';
import { Wizard } from 'patternfly-react';
import { wizardCustomerDetailsContents } from './ValidationWizardItems';
import ValidationWizardReviewStepsManager from './ValidationWizardReviewStepsManager';

export const renderWizardSteps = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  onStepClick
) => {
  const activeStep = wizardSteps[activeStepIndex];
  const activeSubStep = activeStep.subSteps[activeSubStepIndex];

  return wizardSteps.map((step, stepIndex) => (
    <Wizard.Step
      key={stepIndex}
      stepIndex={stepIndex}
      step={step.step}
      label={step.label}
      title={step.title}
      activeStep={activeStep && activeStep.step}
      onClick={onStepClick}
    >
      {step.subSteps.map((sub, subStepIndex) => (
        <Wizard.SubStep
          key={subStepIndex}
          subStep={sub.subStep}
          title={sub.title}
          activeSubStep={activeSubStep && activeSubStep.subStep}
        />
      ))}
    </Wizard.Step>
  ));
};

export const renderSidebarItems = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  onSidebarItemClick
) => {
  const activeStep = wizardSteps[activeStepIndex];
  const activeSubStep = activeStep.subSteps[activeSubStepIndex];

  return wizardSteps.map((step, stepIndex) => (
    <Wizard.SidebarGroup
      key={stepIndex}
      step={step.step}
      activeStep={activeStep.step}
    >
      {step.subSteps.map((sub, subStepIndex) => (
        <Wizard.SidebarGroupItem
          key={subStepIndex}
          stepIndex={stepIndex}
          subStepIndex={subStepIndex}
          subStep={sub.subStep}
          label={sub.label}
          title={sub.title}
          activeSubStep={activeSubStep.subStep}
          onClick={onSidebarItemClick}
        />
      ))}
    </Wizard.SidebarGroup>
  ));
};

export const renderWizardContents = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  room,
  customer
) =>
  wizardSteps.map((step, stepIndex) =>
    step.subSteps.map((sub, subStepIndex) => {
      if (stepIndex === 0) {
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            {wizardCustomerDetailsContents(room, customer)}
          </Wizard.Contents>
        );
      } else if (stepIndex === 1 ) {
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            <ValidationWizardReviewStepsManager
                steps={wizardSteps}
                userid={customer.id} />
          </Wizard.Contents>
        );
      } else if (stepIndex === 2) {
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            <ValidationWizardBookContents
              active={
                stepIndex === activeStepIndex &&
                subStepIndex === activeSubStepIndex
              }
            />
          </Wizard.Contents>
        );
      }
      return null;
    })
  );
